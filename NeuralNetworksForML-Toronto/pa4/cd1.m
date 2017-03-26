function ret = cd1(rbm_w, visible_data)
% <rbm_w> is a matrix of size <number of hidden units> by <number of visible units>
% <visible_data> is a (possibly but not necessarily binary) matrix of size <number of visible units> by <number of data cases>
% The returned value is the gradient approximation produced by CD-1. It's of the same shape as <rbm_w>.
    #error('not yet implemented');
    #1 Take a training sample v, compute the probabilities of the hidden units and sample a hidden activation vector h from this probability distribution.
    
    visible_data = sample_bernoulli(visible_data);
    hidden_probability = visible_state_to_hidden_probabilities(rbm_w,visible_data);
    
    hidden_sample = sample_bernoulli(hidden_probability);
    
    #2 Compute the outer product of v and h and call this the positive gradient.
    pg = configuration_goodness_gradient(visible_data, hidden_sample);
    
    #3 From h, sample a reconstruction v' of the visible units, then resample the hidden activations h' from this. (Gibbs sampling step)
    visible_probability = hidden_state_to_visible_probabilities(rbm_w, hidden_sample);
    visible_sample = sample_bernoulli(visible_probability);
    
    #4 Compute the outer product of v' and h' and call this the negative gradient.
    hidden_probabilities = visible_state_to_hidden_probabilities(rbm_w, visible_sample);
    ng = configuration_goodness_gradient(visible_sample, hidden_probabilities);
    #5Let the update to the weight matrix W {\displaystyle W} W be the positive gradient minus the negative gradient, times some learning rate: Δ W = ϵ ( v h T − v ′ h ′ T ) {\displaystyle \Delta W=\epsilon (vh^{\mathsf {T}}-v'h'^{\mathsf {T}})} {\displaystyle \Delta W=\epsilon (vh^{\mathsf {T}}-v'h'^{\mathsf {T}})}.
    ret = pg - ng;
    #6Update the biases a and b analogously: Δ a = ϵ ( v − v ′ ) {\displaystyle \Delta a=\epsilon (v-v')} {\displaystyle \Delta a=\epsilon (v-v')}, Δ b = ϵ ( h − h ′ ) {\displaystyle \Delta b=\epsilon (h-h')} {\displaystyle \Delta b=\epsilon (h-h')}.

    
    
end
