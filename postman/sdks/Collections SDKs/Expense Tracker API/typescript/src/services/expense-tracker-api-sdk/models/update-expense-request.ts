import { z } from 'zod';

/**
 * Zod schema for the UpdateExpenseRequest model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const updateExpenseRequest = z.lazy(() => {
  return z.object({
    description: z.string().optional().nullable(),
    amount: z.number().optional().nullable(),
    additionalProperties: z.record(z.string(), z.unknown()).optional(),
  });
});

/**
 * @typedef {UpdateExpenseRequest} updateExpenseRequest
 * @property {string} description
 * @property {number} amount
 */
export type UpdateExpenseRequest = z.infer<typeof updateExpenseRequest>;

/**
 * Zod schema for mapping API responses to the UpdateExpenseRequest application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const updateExpenseRequestResponse = z.lazy(() => {
  return z
    .object({
      description: z.string().optional().nullable(),
      amount: z.number().optional().nullable(),
    })
    .passthrough()
    .transform((data) => {
      const additionalProperties: { [key: string]: unknown } = {};
      const declaredKeys = new Set<string>(['description', 'amount']);
      for (const key of globalThis.Object.keys(data)) {
        if (!declaredKeys.has(key)) {
          additionalProperties[key] = (data as { [key: string]: unknown })[key];
        }
      }
      return {
        description: data['description'],
        amount: data['amount'],
        additionalProperties,
      };
    });
});

/**
 * Zod schema for mapping the UpdateExpenseRequest application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const updateExpenseRequestRequest = z.lazy(() => {
  return z
    .object({
      description: z.string().optional().nullable(),
      amount: z.number().optional().nullable(),
      additionalProperties: z.record(z.string(), z.unknown()).optional(),
    })
    .transform((data) => ({
      ...(data['additionalProperties'] ?? {}),
      description: data['description'],
      amount: data['amount'],
    }));
});
