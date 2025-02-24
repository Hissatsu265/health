
# Dataset tùy chỉnh
class CustomImageDataset(Dataset):
    def __init__(self, root_dir, transform=None):
        self.root_dir = root_dir
        self.transform = transform
        self.images = []
        self.labels = []
        self.class_map = {"class_0": 0, "class_1": 1}
        
        for class_name in self.class_map:
            class_dir = os.path.join(root_dir, class_name)
            for file in os.listdir(class_dir):
                self.images.append(os.path.join(class_dir, file))
                self.labels.append(self.class_map[class_name])
    
    def __len__(self):
        return len(self.images)
    
    def __getitem__(self, idx):
        img_path = self.images[idx]
        image = Image.open(img_path).convert("RGB")
        label = self.labels[idx]
        
        if self.transform:
            image = self.transform(image)
        
        return image, torch.tensor(label, dtype=torch.long)

# Mô hình ResNet tùy chỉnh
class CustomResNet(pl.LightningModule):
    def __init__(self, num_classes=2, lr=1e-3):
        super(CustomResNet, self).__init__()
        self.save_hyperparameters()
        self.model = models.resnet18(pretrained=True)
        in_features = self.model.fc.in_features
        self.model.fc = nn.Linear(in_features, num_classes)
        self.loss_fn = nn.CrossEntropyLoss()
        self.lr = lr
    
    def forward(self, x):
        return self.model(x)
    
    def training_step(self, batch, batch_idx):
        images, labels = batch
        outputs = self(images)
        loss = self.loss_fn(outputs, labels)
        self.log("train_loss", loss)
        return loss
    
    def validation_step(self, batch, batch_idx):
        images, labels = batch
        outputs = self(images)
        loss = self.loss_fn(outputs, labels)
        self.log("val_loss", loss, prog_bar=True)
    
    def configure_optimizers(self):
        return optim.Adam(self.parameters(), lr=self.lr)

# Transform ảnh
transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
])

# Load dataset
train_dataset = CustomImageDataset("./data/train", transform=transform)
val_dataset = CustomImageDataset("./data/val", transform=transform)
train_loader = DataLoader(train_dataset, batch_size=32, shuffle=True, num_workers=4)
val_loader = DataLoader(val_dataset, batch_size=32, num_workers=4)

# Huấn luyện
model = CustomResNet(num_classes=2)
trainer = pl.Trainer(max_epochs=10, accelerator="gpu" if torch.cuda.is_available() else "cpu")
trainer.fit(model, train_loader, val_loader)
