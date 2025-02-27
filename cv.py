import torch
import torch.optim as optim
import torchvision.models as models
import torchvision.transforms as transforms
from torchvision.datasets import CIFAR10
from torch.utils.data import DataLoader
import numpy as np
import cv2
import matplotlib.pyplot as plt
import torch.nn as nn
# Load Pretrained ResNet18
class CNN_ResNet(nn.Module):
    def __init__(self, num_classes=10):
        super(CNN_ResNet, self).__init__()
        self.resnet = models.resnet18(pretrained=True)
        self.resnet.fc = nn.Linear(self.resnet.fc.in_features, num_classes)
    
    def forward(self, x):
        return self.resnet(x)

# GradCAM để trực quan hóa các vùng quan trọng
class GradCAM:
    def __init__(self, model, target_layer):
        self.model = model
        self.target_layer = target_layer
        self.gradients = None
        target_layer.register_forward_hook(self.save_activation)
        target_layer.register_backward_hook(self.save_gradient)

    def save_activation(self, module, input, output):
        self.activation = output
    
    def save_gradient(self, module, grad_input, grad_output):
        self.gradients = grad_output[0]
    
    def generate_cam(self, input_tensor, class_idx):
        self.model.zero_grad()
        output = self.model(input_tensor)
        output[:, class_idx].backward()
        gradients = self.gradients.mean(dim=[2, 3], keepdim=True)
        cam = torch.relu((self.activation * gradients).sum(dim=1)).detach().cpu().numpy()
        cam = cv2.resize(cam[0], (32, 32))
        cam = (cam - cam.min()) / (cam.max() - cam.min())
        return cam

# Chuẩn bị dữ liệu
transform = transforms.Compose([
    transforms.ToTensor(),
    transforms.Normalize((0.5,), (0.5,))
])
dataset = CIFAR10(root='./data', train=True, transform=transform, download=True)
dataloader = DataLoader(dataset, batch_size=64, shuffle=True)

# Khởi tạo mô hình, loss, optimizer
model = CNN_ResNet()
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

# Huấn luyện nhanh 1 epoch
def train(model, dataloader, criterion, optimizer):
    model.train()
    for images, labels in dataloader:
        optimizer.zero_grad()
        outputs = model(images)
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()
train(model, dataloader, criterion, optimizer)

# Test GradCAM
image, label = dataset[0]
image = image.unsqueeze(0)  # Thêm batch dimension
model.eval()

gradcam = GradCAM(model, model.resnet.layer4)
cam = gradcam.generate_cam(image, label)

# Vẽ ảnh gốc và GradCAM
plt.subplot(1, 2, 1)
plt.imshow(image.squeeze().permute(1, 2, 0))
plt.title('Original Image')

plt.subplot(1, 2, 2)
plt.imshow(cam, cmap='jet', alpha=0.5)
plt.title('GradCAM')
plt.show()
