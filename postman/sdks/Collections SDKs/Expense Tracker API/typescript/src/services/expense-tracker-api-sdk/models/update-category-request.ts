import { z } from 'zod';

/**
 * Zod schema for the UpdateCategoryRequest model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const updateCategoryRequest = z.lazy(() => {
  return z.object({
    receiptThreshold: z.number().optional().nullable(),
    additionalProperties: z.record(z.string(), z.unknown()).optional(),
  });
});

/**
 * @typedef {UpdateCategoryRequest} updateCategoryRequest
 * @property {number} receiptThreshold
 */
export type UpdateCategoryRequest = z.infer<typeof updateCategoryRequest>;

/**
 * Zod schema for mapping API responses to the UpdateCategoryRequest application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const updateCategoryRequestResponse = z.lazy(() => {
  return z
    .object({
      receiptThreshold: z.number().optional().nullable(),
    })
    .passthrough()
    .transform((data) => {
      const additionalProperties: { [key: string]: unknown } = {};
      const declaredKeys = new Set<string>(['receiptThreshold']);
      for (const key of globalThis.Object.keys(data)) {
        if (!declaredKeys.has(key)) {
          additionalProperties[key] = (data as { [key: string]: unknown })[key];
        }
      }
      return {
        receiptThreshold: data['receiptThreshold'],
        additionalProperties,
      };
    });
});

/**
 * Zod schema for mapping the UpdateCategoryRequest application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const updateCategoryRequestRequest = z.lazy(() => {
  return z
    .object({
      receiptThreshold: z.number().optional().nullable(),
      additionalProperties: z.record(z.string(), z.unknown()).optional(),
    })
    .transform((data) => ({
      ...(data['additionalProperties'] ?? {}),
      receiptThreshold: data['receiptThreshold'],
    }));
});
