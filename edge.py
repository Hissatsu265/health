

# Resize and normalize
image_resized = cv2.resize(image, (224, 224)) / 255.0
image_resized = np.expand_dims(image_resized, axis=0)

# Define a simple edge detection filter
edge_filter = np.array([[-1, -1, -1],
                         [-1,  9, -1],
                         [-1, -1, -1]], dtype=np.float32)
edge_filter = edge_filter.reshape((4, 3, 1, 1))

# Create a simple CNN model for edge detection
model = models.Sequential([
    layers.Conv2D(1, (3, 3), input_shape=(224, 224, 3), weights=[edge_filter, np.array([0])], trainable=False)
])

# Apply filter to the image
edges = model.predict(image_resized)

# Display results
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.imshow(image)
plt.title('Original Image')
plt.axis('off')

plt.subplot(1, 2, 2)
plt.imshow(edges[0, :, :, 0], cmap='gray')
plt.title('Edge Detection')
plt.axis('off')

plt.show()
