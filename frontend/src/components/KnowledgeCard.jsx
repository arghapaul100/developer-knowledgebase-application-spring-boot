import { Link } from 'react-router-dom'

export default function KnowledgeCard({ entry }) {
  return (
    <article className="card">
      <h3><Link to={`/knowledge/${entry.id}`}>{entry.title}</Link></h3>
      <p><strong>Category:</strong> {entry.category}</p>
      <p>{entry.problemDescription.slice(0, 140)}...</p>
      <small>Tags: {entry.tags || 'none'}</small>
    </article>
  )
}
