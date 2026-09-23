# 🌌 Expense Tracker Service API

> *From business requirements to a running service — your mission, should you choose to accept it.*

![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![REST](https://img.shields.io/badge/REST-API-orange?style=for-the-badge)
![JWT](https://img.shields.io/badge/Auth-JWT-black?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

---

<p align="center">
  <img src="https://voyager.postman.com/illustration/postman-galaxy-illustration.svg"
       alt="Postman Galaxy"
       width="480"/>
</p>

---

## 🛸 Overview

This repository is your launchpad for learning how modern APIs get designed, validated, documented and built — in that order.

It starts with something most tutorials skip: a **business requirements document**. Finance Operations at Northbeam Consulting need to replace a spreadsheet-and-email expense process, and they have written down what the service must do without saying a word about how the API should look. That is your starting point.

From there you will design the API as a **Postman Collection**, generate its **specification**, write the **validations before any code exists**, and only then implement a service that satisfies them. Every one of those artifacts lives in this repository, versioned alongside the code — so the design and the implementation can never quietly drift apart.

Authentication is handled via **JWT Bearer tokens** (HS256). The service has three roles — Employee, Approver and Finance — and the token is what tells them apart.


---

## 🗺️ The chain of truth

Each artifact in this repository derives from the one before it, and each one is checkable against its parent.

| Artifact | Answers | Derived from |
| --- | --- | --- |
| **Business requirements** | What must the service do? | The business |
| **Collection** | How is the API shaped? | The requirements |
| **Specification** | What is the formal contract? | The collection |
| **Validations** | Does it behave as promised? | The collection and its examples |
| **Implementation** | Does it actually work? | All of the above |

Treat the collection the way an engineer treats an OpenAPI spec: your implementation is correct when every request returns a response matching the documented examples, and every validation passes.

---

## 📋 What the service has to do

Read the requirements document for the full picture. In short:

- **Expenses** — employees record what they spent, in the currency they spent it, against exactly one category, with optional receipt images attached
- **Categories** — maintained centrally by Finance; each carries the amount above which a receipt becomes mandatory
- **Claims** — employees group unclaimed expenses into a claim and submit it; an approver approves it or rejects it with a reason; Finance marks approved claims as reimbursed

The rules are where it gets interesting, and where your validations will earn their keep:

- Amounts must be positive; dates cannot be in the future
- Expenses older than 90 days cannot be added to a claim
- A receipt is mandatory above a category's threshold
- A claim moves through `draft → submitted → approved | rejected → reimbursed` and nowhere else
- Expenses inside a submitted claim are frozen
- An approver cannot approve their own claim
- A reimbursed claim is final

---

## 🔐 Authentication

All endpoints are protected. No part of the service may be reachable anonymously, and callers must only reach what their role permits — an attempt to exceed it is refused, not silently filtered.

**Secrets never live in this repository.** Keep the signing secret in a Postman Vault or a secret environment variable, and in CI keep it in a repository secret. If a token or key has ever been committed here, rotate it.

---

## 🚀 Getting Started

### Prerequisites

- [Postman desktop app](https://www.postman.com/downloads/) — required, since connecting a workspace to a local Git repository needs filesystem access the browser cannot give it
- Git, and a GitHub account
- Python 3.x
- Basic familiarity with REST APIs and HTTP

### Workflow

1. **Read the requirements** in `docs/`. Note what they specify and, more importantly, what they leave to you.
2. **Clone this repository** and connect it to a Postman workspace — in the desktop app, choose local files and open this folder. Postman creates the `postman/` directory for you.
3. **Design the API** as a collection: resources, paths, methods, and an example response for every outcome including the failures.
4. **Generate the specification** from your collection, and keep the two in sync as the design moves.
5. **Write the validations** — before you write any implementation. Run them. Everything fails. That is the correct starting state.
6. **Build your service** in `app/`, then run the validations again and watch the red turn green.
7. **Wire up continuous validation** so every push runs the collection automatically.

---

## 🤖 Using an LLM Agent

A core goal of this project is hands-on practice going **from requirements to implementation with the help of an AI coding agent**. Both the requirements document and the collection are excellent inputs — one describes intent, the other describes structure.

Some things worth exploring as you work:

- How well does the agent turn business requirements into an API design? Which decisions does it make for you, and do you agree with them?
- How well does it read the collection as a specification once that design exists?
- Can it write validations for the business rules, not just the status codes? The claim state machine is a good test.
- How do you check that generated code actually satisfies the contract, rather than merely running?
- What happens when the agent quietly resolves something the requirements deliberately left open?

There are no prescribed steps here — experimentation and reflection are part of the learning.

---

## 📬 Working with the Collection in Postman

<p align="center">
  <img src="https://voyager.postman.com/illustration/postman-loves-your-workflow-illustration.svg"
       alt="Postman workflow illustration"
       width="420"/>
</p>

Postman lets you exercise the contract before you write a single line of code:

- Use the **Examples** saved on each request to pin down expected inputs and outputs
- Use a **Mock Server** to make the design answer over HTTP before any implementation exists
- Use the **Collection Runner** to execute everything in sequence and get one verdict
- Use the **Authorization** tab to inspect the JWT configuration
- Use the **Postman CLI** to run the same collection in CI, so nothing merges unvalidated

---

## ♻️ Continuous validation

Once the pipeline is in place, every push runs the collection against your service. The point is not that the API works today — it is that you find out the moment it stops working.

Generate the workflow from the Collection Runner rather than writing YAML from memory: choose the Postman CLI and the GitHub Actions template, then store your Postman API key as a repository secret. GitHub is the example used here; the Git integration itself is provider-agnostic.

---

## 📚 Resources

- [Postman Learning Center](https://learning.postman.com/)
- [Postman Collection Format Docs](https://learning.postman.com/collection/v2.1.0/)
- [Postman CLI](https://learning.postman.com/docs/postman-cli/postman-cli-overview/)
- [JWT Introduction](https://jwt.io/introduction)
- [HTTP Status Codes Reference](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)

---

<p align="center">
  <em>The requirements are written. The contract is yours to draw. 🧾✨🌌</em>
</p>
