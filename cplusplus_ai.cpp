#include <iostream>
#include <vector>
#include <cmath>
#include <cstdlib>
#include <ctime>

class SimpleNeuralNetwork {
private:
    std::vector<double> weights;
    double bias;

    double sigmoid(double x) {
        return 1 / (1 + exp(-x));
    }

    double sigmoid_derivative(double x) {
        return x * (1 - x);
    }

public:
    SimpleNeuralNetwork(int input_size) {
        srand(time(0));
        for (int i = 0; i < input_size; i++) {
            weights.push_back((double)rand() / RAND_MAX * 2 - 1);
        }
        bias = (double)rand() / RAND_MAX * 2 - 1;
    }

    double predict(const std::vector<double>& inputs) {
        double sum = bias;
        for (size_t i = 0; i < weights.size(); i++) {
            sum += weights[i] * inputs[i];
        }
        return sigmoid(sum);
    }

    void train(const std::vector<std::vector<double>>& training_inputs, const std::vector<double>& training_outputs, int epochs, double learning_rate) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (size_t i = 0; i < training_inputs.size(); i++) {
                double output = predict(training_inputs[i]);
                double error = training_outputs[i] - output;
                
                for (size_t j = 0; j < weights.size(); j++) {
                    weights[j] += learning_rate * error * sigmoid_derivative(output) * training_inputs[i][j];
                }
                bias += learning_rate * error * sigmoid_derivative(output);
            }
        }
    }
};

int main() {
    std::vector<std::vector<double>> inputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    std::vector<double> outputs = {0, 1, 1, 0};
    
    SimpleNeuralNetwork nn(2);
    nn.train(inputs, outputs, 5000, 0.1);
    std::cout << "Starting"<<std::endl;
    std::cout << "Predictions: " << std::endl;
    for (const auto& input : inputs) {
        std::cout << "Input: (" << input[0] << ", " << input[1] << ") -> Output: " << nn.predict(input) << std::endl;
    }
    
    return 0;
}
