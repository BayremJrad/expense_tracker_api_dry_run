import { z } from 'zod';

/**
 * Zod schema for the CreateClaimRequest model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const createClaimRequest = z.lazy(() => {
  return z.object({
    title: z.string().optional().nullable(),
    expenseIds: z.array(z.string()).optional().nullable(),
    additionalProperties: z.record(z.string(), z.unknown()).optional(),
  });
});

/**
 * @typedef {CreateClaimRequest} createClaimRequest
 * @property {string} title
 * @property {string[]} expenseIds
 */
export type CreateClaimRequest = z.infer<typeof createClaimRequest>;

/**
 * Zod schema for mapping API responses to the CreateClaimRequest application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createClaimRequestResponse = z.lazy(() => {
  return z
    .object({
      title: z.string().optional().nullable(),
      expenseIds: z.array(z.string()).optional().nullable(),
    })
    .passthrough()
    .transform((data) => {
      const additionalProperties: { [key: string]: unknown } = {};
      const declaredKeys = new Set<string>(['title', 'expenseIds']);
      for (const key of globalThis.Object.keys(data)) {
        if (!declaredKeys.has(key)) {
          additionalProperties[key] = (data as { [key: string]: unknown })[key];
        }
      }
      return {
        title: data['title'],
        expenseIds: data['expenseIds'],
        additionalProperties,
      };
    });
});

/**
 * Zod schema for mapping the CreateClaimRequest application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createClaimRequestRequest = z.lazy(() => {
  return z
    .object({
      title: z.string().optional().nullable(),
      expenseIds: z.array(z.string()).optional().nullable(),
      additionalProperties: z.record(z.string(), z.unknown()).optional(),
    })
    .transform((data) => ({
      ...(data['additionalProperties'] ?? {}),
      title: data['title'],
      expenseIds: data['expenseIds'],
    }));
});
