from transformers import pipeline
import time
import random

def sentiment_analysis(text):
    classifier = pipeline("sentiment-analysis")
    result = classifier(text)
    return result

def generate_random_text():
    texts = [
        "I love using AI models for NLP tasks!",
        "This movie was absolutely fantastic!",
        "I had the worst experience with this product.",
        "The food at the restaurant was delicious and well-prepared.",
        "I don't think this app is very user-friendly.",
        "The weather today is just perfect for a walk.",
        "I am extremely disappointed with the customer service.",
        "Studying machine learning is both fun and challenging.",
        "The concert last night was an unforgettable experience.",
        "My flight got delayed for 5 hours, and I am frustrated."
    ]
    return random.choice(texts)

def analyze_multiple_texts(n):
    results = []
    for _ in range(n):
        text = generate_random_text()
        result = sentiment_analysis(text)
        results.append((text, result))
        time.sleep(0.5)  # Simulating processing time
    return results

def main():
    print("Starting Sentiment Analysis...")
    num_texts = 10
    results = analyze_multiple_texts(num_texts)
    
    for idx, (text, result) in enumerate(results):
        print(f"Text {idx + 1}: {text}")
        print(f"Sentiment: {result}\n")
    
    print("Analysis completed!")

if __name__ == "__main__":
    main()
