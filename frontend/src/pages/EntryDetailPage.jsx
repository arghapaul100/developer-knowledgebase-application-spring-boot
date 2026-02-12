import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter'
import api from '../api/client'

export default function EntryDetailPage() {
  const { id } = useParams()
  const [entry, setEntry] = useState(null)

  useEffect(() => {
    api.get(`/knowledge/${id}`).then(({ data }) => setEntry(data))
  }, [id])

  if (!entry) return <p>Loading...</p>

  return (
    <div className="stack">
      <h1>{entry.title}</h1>
      <p><strong>Category:</strong> {entry.category}</p>
      <p><strong>Problem:</strong> {entry.problemDescription}</p>
      <p><strong>Solution:</strong> {entry.solutionDescription}</p>
      {entry.codeSnippet && <SyntaxHighlighter language="java">{entry.codeSnippet}</SyntaxHighlighter>}
      <p><strong>Tags:</strong> {entry.tags}</p>
      <Link to={`/edit/${entry.id}`}>Edit</Link>
    </div>
  )
}
