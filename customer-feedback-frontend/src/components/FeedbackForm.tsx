// customer-feedback-frontend/src/components/FeedbackForm.tsx

import React, { useState } from 'react';
import axios from 'axios';

const FeedbackForm: React.FC = () => {
  const [email, setEmail] = useState('');
  const [content, setContent] = useState('');
  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axios.post('/api/feedback', { email, content });
      setMessage('Feedback submitted successfully!');
      setIsError(false);
      setEmail('');
      setContent('');
    } catch (error) {
      setMessage('Error submitting feedback. Please try again.');
      setIsError(true);
    }
  };

  return (
    <div className="card">
      <div className="card-body">
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email address</label>
            <input
              type="email"
              className="form-control"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="content" className="form-label">Feedback</label>
            <textarea
              className="form-control"
              id="content"
              rows={3}
              value={content}
              onChange={(e) => setContent(e.target.value)}
              required
            ></textarea>
          </div>
          <button type="submit" className="btn btn-primary">Submit Feedback</button>
        </form>
        {message && (
          <div className={`mt-3 alert ${isError ? 'alert-danger' : 'alert-success'}`} role="alert">
            {message}
          </div>
        )}
      </div>
    </div>
  );
};

export default FeedbackForm;
