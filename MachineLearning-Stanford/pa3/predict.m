function p = predict(Theta1, Theta2, X)
%PREDICT Predict the label of an input given a trained neural network
%   p = PREDICT(Theta1, Theta2, X) outputs the predicted label of X given the
%   trained weights of a neural network (Theta1, Theta2)

% Useful values
m = size(X, 1);
num_labels = size(Theta2, 1);

% You need to return the following variables correctly 
p = zeros(size(X, 1), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Complete the following code to make predictions using
%               your learned neural network. You should set p to a 
%               vector containing labels between 1 to num_labels.
%
% Hint: The max function might come in useful. In particular, the max
%       function can also return the index of the max element, for more
%       information see 'help max'. If your examples are in rows, then, you
%       can use max(A, [], 2) to obtain the max for each row.
%
% X 5000x400,Y 5000, theta1 25x401 theta2 10x26
a1 = [ones(m, 1) X]; %a1 5000x401

%fprintf('\nsize of a1: %d x %d\n',size(a1));

z2 = a1 * Theta1';
a2 = sigmoid(z2); %a2 5000x26
a2 = [ones(m,1) a2]; %z2 5000x26
%fprintf('\nsize of z2: %d x %d\n',size(z2));
%fprintf('\nsize of a2: %d x %d\n',size(a2));

z3 = a2 * Theta2'; %z3 5000x10 
a3 = sigmoid(z3); %a3=h(x) 5000x10

%fprintf('\nsize of z3: %d x %d\n',size(z3));
%fprintf('\nsize of a3: %d x %d\n',size(a3));

[val p] = max(a3,[],2);
%fprintf('\nsize of p: %d x %d\n',size(p));

% =========================================================================


end
