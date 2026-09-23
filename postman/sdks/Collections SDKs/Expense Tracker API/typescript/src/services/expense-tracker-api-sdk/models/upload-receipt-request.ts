import { z } from 'zod';

/**
 * Zod schema for the UploadReceiptRequest model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const uploadReceiptRequest = z.lazy(() => {
  return z.object({
    file: z.instanceof(ArrayBuffer).optional().nullable(),
    filename: z.string().optional().nullable(),
  });
});

/**
 * @typedef {UploadReceiptRequest} uploadReceiptRequest
 * @property {ArrayBuffer} file - The receipt image file. Accepted: JPEG, PNG, PDF. Max 10 MB.
 * @property {string} filename - Optional display filename override.
 */
export type UploadReceiptRequest = z.infer<typeof uploadReceiptRequest>;

/**
 * Zod schema for mapping API responses to the UploadReceiptRequest application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const uploadReceiptRequestResponse = z.lazy(() => {
  return z
    .object({
      file: z.instanceof(ArrayBuffer).optional().nullable(),
      filename: z.string().optional().nullable(),
    })
    .transform((data) => ({
      file: data['file'],
      filename: data['filename'],
    }));
});

/**
 * Zod schema for mapping the UploadReceiptRequest application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const uploadReceiptRequestRequest = z.lazy(() => {
  return z
    .object({
      file: z.instanceof(ArrayBuffer).optional().nullable(),
      filename: z.string().optional().nullable(),
    })
    .transform((data) => ({
      file: data['file'],
      filename: data['filename'],
    }));
});
