const http = require('http');
const PORT = process.env.PORT || 4510;

const server = http.createServer((req, res) => {
  const { method, url } = req;
  const urlObj = new URL(url, 'http://localhost');
  const pathname = urlObj.pathname;

  // Helper to parse path params
  function matchPath(pattern, path) {
    const patternParts = pattern.split('/');
    const pathParts = path.split('/');
    if (patternParts.length !== pathParts.length) return null;
    const params = {};
    for (let i = 0; i < patternParts.length; i++) {
      if (patternParts[i].startsWith(':')) {
        params[patternParts[i].slice(1)] = pathParts[i];
      } else if (patternParts[i] !== pathParts[i]) {
        return null;
      }
    }
    return params;
  }

  // ─── CATEGORIES ───────────────────────────────────────────────────────────

  // @endpoint GET /categories
  if (method === 'GET' && pathname === '/categories') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      data: [
        { id: 'cat_01', name: 'Travel', receiptThreshold: 50.00, requiresReceiptAlways: false, requiresClientReference: false, active: true },
        { id: 'cat_02', name: 'Accommodation', receiptThreshold: 0.01, requiresReceiptAlways: true, requiresClientReference: false, active: true },
        { id: 'cat_03', name: 'Meals', receiptThreshold: 25.00, requiresReceiptAlways: false, requiresClientReference: false, active: true },
        { id: 'cat_04', name: 'Software', receiptThreshold: 10.00, requiresReceiptAlways: false, requiresClientReference: false, active: true },
        { id: 'cat_05', name: 'Client Entertainment', receiptThreshold: 0.01, requiresReceiptAlways: true, requiresClientReference: true, active: true }
      ],
      total: 5
    }));
    return;
  }

  // @endpoint GET /categories/:categoryId
  const getCategoryParams = matchPath('/categories/:categoryId', pathname);
  if (method === 'GET' && getCategoryParams) {
    const { categoryId } = getCategoryParams;
    if (categoryId === 'cat_01') {
      res.writeHead(200, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({
        id: 'cat_01', name: 'Travel', receiptThreshold: 50.00,
        requiresReceiptAlways: false, requiresClientReference: false,
        active: true, createdAt: '2025-01-01T00:00:00Z', updatedAt: '2025-01-01T00:00:00Z'
      }));
    } else {
      res.writeHead(404, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({ error: { code: 'NOT_FOUND', message: 'Category not found.' } }));
    }
    return;
  }

  // @endpoint POST /categories
  if (method === 'POST' && pathname === '/categories') {
    res.writeHead(201, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: 'cat_05', name: 'Client Entertainment', receiptThreshold: 0.01,
      requiresReceiptAlways: true, requiresClientReference: true,
      active: true, createdAt: '2026-09-23T10:00:00Z', updatedAt: '2026-09-23T10:00:00Z'
    }));
    return;
  }

  // @endpoint PATCH /categories/:categoryId
  const patchCategoryParams = matchPath('/categories/:categoryId', pathname);
  if (method === 'PATCH' && patchCategoryParams) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: patchCategoryParams.categoryId, name: 'Travel', receiptThreshold: 75.00,
      requiresReceiptAlways: false, requiresClientReference: false,
      active: true, createdAt: '2025-01-01T00:00:00Z', updatedAt: '2026-09-23T10:05:00Z'
    }));
    return;
  }

  // ─── EXPENSES ─────────────────────────────────────────────────────────────

  // @endpoint GET /expenses
  if (method === 'GET' && pathname === '/expenses') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      data: [
        {
          id: 'exp_001', amount: 120.50, currency: 'GBP', amountEur: 142.19,
          dateIncurred: '2026-09-10', category: { id: 'cat_01', name: 'Travel' },
          description: 'Train to Manchester client site', paymentMethod: 'personal',
          clientReference: null, receiptCount: 1, claimId: null, ownerId: 'usr_123',
          recordedAt: '2026-09-10T18:30:00Z'
        },
        {
          id: 'exp_002', amount: 45.00, currency: 'EUR', amountEur: 45.00,
          dateIncurred: '2026-09-11', category: { id: 'cat_03', name: 'Meals' },
          description: 'Team lunch during client workshop', paymentMethod: 'personal',
          clientReference: 'CLIENT-007', receiptCount: 1, claimId: null, ownerId: 'usr_123',
          recordedAt: '2026-09-11T13:00:00Z'
        }
      ],
      pagination: { page: 1, pageSize: 20, total: 2, totalPages: 1 }
    }));
    return;
  }

  // @endpoint POST /expenses
  if (method === 'POST' && pathname === '/expenses') {
    res.writeHead(201, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: 'exp_001', amount: 120.50, currency: 'GBP', amountEur: 142.19,
      dateIncurred: '2026-09-10', category: { id: 'cat_01', name: 'Travel' },
      description: 'Train to Manchester client site', paymentMethod: 'personal',
      clientReference: null, receiptCount: 0, claimId: null, ownerId: 'usr_123',
      recordedAt: '2026-09-10T18:30:00Z'
    }));
    return;
  }

  // @endpoint GET /expenses/:expenseId
  const getExpenseParams = matchPath('/expenses/:expenseId', pathname);
  if (method === 'GET' && getExpenseParams && !pathname.includes('/receipts')) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: getExpenseParams.expenseId, amount: 120.50, currency: 'GBP', amountEur: 142.19,
      dateIncurred: '2026-09-10', category: { id: 'cat_01', name: 'Travel' },
      description: 'Train to Manchester client site', paymentMethod: 'personal',
      clientReference: null,
      receipts: [{ id: 'rec_001', filename: 'train_receipt.jpg', mimeType: 'image/jpeg', sizeBytes: 204800, uploadedAt: '2026-09-10T18:35:00Z' }],
      claimId: null, ownerId: 'usr_123', recordedAt: '2026-09-10T18:30:00Z'
    }));
    return;
  }

  // @endpoint PATCH /expenses/:expenseId
  const patchExpenseParams = matchPath('/expenses/:expenseId', pathname);
  if (method === 'PATCH' && patchExpenseParams && !pathname.includes('/receipts')) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: patchExpenseParams.expenseId, amount: 135.00, currency: 'GBP', amountEur: 159.57,
      dateIncurred: '2026-09-10', category: { id: 'cat_01', name: 'Travel' },
      description: 'Train to Manchester client site (return)', paymentMethod: 'personal',
      clientReference: null, receiptCount: 1, claimId: null, ownerId: 'usr_123',
      recordedAt: '2026-09-10T18:30:00Z', updatedAt: '2026-09-23T09:00:00Z'
    }));
    return;
  }

  // @endpoint DELETE /expenses/:expenseId
  const deleteExpenseParams = matchPath('/expenses/:expenseId', pathname);
  if (method === 'DELETE' && deleteExpenseParams && !pathname.includes('/receipts')) {
    res.writeHead(204);
    res.end();
    return;
  }

  // ─── RECEIPTS ─────────────────────────────────────────────────────────────

  // @endpoint GET /expenses/:expenseId/receipts
  const listReceiptsParams = matchPath('/expenses/:expenseId/receipts', pathname);
  if (method === 'GET' && listReceiptsParams) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      data: [
        { id: 'rec_001', expenseId: listReceiptsParams.expenseId, filename: 'train_receipt.jpg', mimeType: 'image/jpeg', sizeBytes: 204800, uploadedAt: '2026-09-10T18:35:00Z', uploadedBy: 'usr_123' }
      ],
      total: 1
    }));
    return;
  }

  // @endpoint POST /expenses/:expenseId/receipts
  const uploadReceiptParams = matchPath('/expenses/:expenseId/receipts', pathname);
  if (method === 'POST' && uploadReceiptParams) {
    res.writeHead(201, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: 'rec_001', expenseId: uploadReceiptParams.expenseId, filename: 'train_receipt.jpg',
      mimeType: 'image/jpeg', sizeBytes: 204800, uploadedAt: '2026-09-10T18:35:00Z', uploadedBy: 'usr_123'
    }));
    return;
  }

  // @endpoint DELETE /expenses/:expenseId/receipts/:receiptId
  const deleteReceiptParams = matchPath('/expenses/:expenseId/receipts/:receiptId', pathname);
  if (method === 'DELETE' && deleteReceiptParams) {
    res.writeHead(204);
    res.end();
    return;
  }

  // ─── CLAIMS ───────────────────────────────────────────────────────────────

  // @endpoint GET /claims
  if (method === 'GET' && pathname === '/claims') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      data: [
        {
          id: 'clm_001', title: 'September 2026 - Client site visits', status: 'draft',
          totalEur: 187.19, expenseCount: 2, ownerId: 'usr_123',
          createdAt: '2026-09-23T09:00:00Z', submittedAt: null, decision: null
        },
        {
          id: 'clm_002', title: 'August 2026 - Travel expenses', status: 'reimbursed',
          totalEur: 430.00, expenseCount: 5, ownerId: 'usr_123',
          createdAt: '2026-08-31T17:00:00Z', submittedAt: '2026-09-01T08:00:00Z',
          decision: { decidedBy: 'usr_456', decidedAt: '2026-09-03T10:00:00Z', outcome: 'approved', reason: null }
        }
      ],
      pagination: { page: 1, pageSize: 20, total: 2, totalPages: 1 }
    }));
    return;
  }

  // @endpoint POST /claims
  if (method === 'POST' && pathname === '/claims') {
    res.writeHead(201, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: 'clm_001', title: 'September 2026 - Client site visits', status: 'draft',
      totalEur: 187.19, expenseCount: 2, ownerId: 'usr_123',
      createdAt: '2026-09-23T09:00:00Z', submittedAt: null, decision: null
    }));
    return;
  }

  // @endpoint GET /claims/:claimId
  const getClaimParams = matchPath('/claims/:claimId', pathname);
  if (method === 'GET' && getClaimParams && !pathname.includes('/submit') && !pathname.includes('/approve') && !pathname.includes('/reject') && !pathname.includes('/reimburse')) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: getClaimParams.claimId, title: 'September 2026 - Client site visits', status: 'draft',
      totalEur: 187.19, ownerId: 'usr_123', createdAt: '2026-09-23T09:00:00Z',
      submittedAt: null, decision: null,
      expenses: [
        { id: 'exp_001', amount: 120.50, currency: 'GBP', amountEur: 142.19, dateIncurred: '2026-09-10', category: { id: 'cat_01', name: 'Travel' }, description: 'Train to Manchester client site', paymentMethod: 'personal', receiptCount: 1 },
        { id: 'exp_002', amount: 45.00, currency: 'EUR', amountEur: 45.00, dateIncurred: '2026-09-11', category: { id: 'cat_03', name: 'Meals' }, description: 'Team lunch during client workshop', paymentMethod: 'personal', receiptCount: 1 }
      ],
      statusHistory: [{ status: 'draft', changedBy: 'usr_123', changedAt: '2026-09-23T09:00:00Z' }]
    }));
    return;
  }

  // @endpoint PATCH /claims/:claimId
  const patchClaimParams = matchPath('/claims/:claimId', pathname);
  if (method === 'PATCH' && patchClaimParams && !pathname.includes('/submit') && !pathname.includes('/approve') && !pathname.includes('/reject') && !pathname.includes('/reimburse')) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: patchClaimParams.claimId, title: 'September 2026 - Client site visits (updated)',
      status: 'draft', totalEur: 312.19, expenseCount: 3, ownerId: 'usr_123',
      createdAt: '2026-09-23T09:00:00Z', updatedAt: '2026-09-23T10:00:00Z'
    }));
    return;
  }

  // @endpoint DELETE /claims/:claimId
  const deleteClaimParams = matchPath('/claims/:claimId', pathname);
  if (method === 'DELETE' && deleteClaimParams && !pathname.includes('/submit') && !pathname.includes('/approve') && !pathname.includes('/reject') && !pathname.includes('/reimburse')) {
    res.writeHead(204);
    res.end();
    return;
  }

  // ─── CLAIM ACTIONS ────────────────────────────────────────────────────────

  // @endpoint POST /claims/:claimId/submit
  const submitClaimParams = matchPath('/claims/:claimId/submit', pathname);
  if (method === 'POST' && submitClaimParams) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: submitClaimParams.claimId, title: 'September 2026 - Client site visits',
      status: 'submitted', totalEur: 187.19, expenseCount: 2, ownerId: 'usr_123',
      createdAt: '2026-09-23T09:00:00Z', submittedAt: '2026-09-23T10:30:00Z', decision: null,
      statusHistory: [
        { status: 'draft', changedBy: 'usr_123', changedAt: '2026-09-23T09:00:00Z' },
        { status: 'submitted', changedBy: 'usr_123', changedAt: '2026-09-23T10:30:00Z' }
      ]
    }));
    return;
  }

  // @endpoint POST /claims/:claimId/approve
  const approveClaimParams = matchPath('/claims/:claimId/approve', pathname);
  if (method === 'POST' && approveClaimParams) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: approveClaimParams.claimId, title: 'September 2026 - Client site visits',
      status: 'approved', totalEur: 320.50, expenseCount: 3, ownerId: 'usr_789',
      createdAt: '2026-09-20T14:00:00Z', submittedAt: '2026-09-23T08:00:00Z',
      decision: { decidedBy: 'usr_456', decidedAt: '2026-09-23T11:00:00Z', outcome: 'approved', reason: null },
      statusHistory: [
        { status: 'draft', changedBy: 'usr_789', changedAt: '2026-09-20T14:00:00Z' },
        { status: 'submitted', changedBy: 'usr_789', changedAt: '2026-09-23T08:00:00Z' },
        { status: 'approved', changedBy: 'usr_456', changedAt: '2026-09-23T11:00:00Z' }
      ]
    }));
    return;
  }

  // @endpoint POST /claims/:claimId/reject
  const rejectClaimParams = matchPath('/claims/:claimId/reject', pathname);
  if (method === 'POST' && rejectClaimParams) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: rejectClaimParams.claimId, title: 'September 2026 - Client site visits',
      status: 'draft', totalEur: 320.50, expenseCount: 3, ownerId: 'usr_789',
      createdAt: '2026-09-20T14:00:00Z', submittedAt: '2026-09-23T08:00:00Z',
      decision: { decidedBy: 'usr_456', decidedAt: '2026-09-23T11:15:00Z', outcome: 'rejected', reason: 'Missing receipt for the Accommodation expense exceeding the threshold.' },
      statusHistory: [
        { status: 'draft', changedBy: 'usr_789', changedAt: '2026-09-20T14:00:00Z' },
        { status: 'submitted', changedBy: 'usr_789', changedAt: '2026-09-23T08:00:00Z' },
        { status: 'rejected', changedBy: 'usr_456', changedAt: '2026-09-23T11:15:00Z' },
        { status: 'draft', changedBy: 'system', changedAt: '2026-09-23T11:15:01Z', note: 'Returned to draft after rejection (BR-24)' }
      ]
    }));
    return;
  }

  // @endpoint POST /claims/:claimId/reimburse
  const reimburseClaimParams = matchPath('/claims/:claimId/reimburse', pathname);
  if (method === 'POST' && reimburseClaimParams) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      id: reimburseClaimParams.claimId, title: 'September 2026 - Client site visits',
      status: 'reimbursed', totalEur: 320.50, expenseCount: 3, ownerId: 'usr_789',
      createdAt: '2026-09-20T14:00:00Z', submittedAt: '2026-09-23T08:00:00Z',
      decision: { decidedBy: 'usr_456', decidedAt: '2026-09-23T11:00:00Z', outcome: 'approved', reason: null },
      reimbursedat: '2026-09-23T14:00:00Z', reimbursedBy: 'usr_finance_001',
      statusHistory: [
        { status: 'draft', changedBy: 'usr_789', changedAt: '2026-09-20T14:00:00Z' },
        { status: 'submitted', changedBy: 'usr_789', changedAt: '2026-09-23T08:00:00Z' },
        { status: 'approved', changedBy: 'usr_456', changedAt: '2026-09-23T11:00:00Z' },
        { status: 'reimbursed', changedBy: 'usr_finance_001', changedAt: '2026-09-23T14:00:00Z' }
      ]
    }));
    return;
  }

  // ─── 404 FALLBACK ─────────────────────────────────────────────────────────
  res.writeHead(404, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ error: 'Mock route not defined', method, url }));
});

server.listen(PORT, () => console.log('Expense Tracker API mock running on port ' + PORT));
