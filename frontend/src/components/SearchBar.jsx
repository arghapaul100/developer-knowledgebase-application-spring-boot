import { useState } from 'react'

export default function SearchBar({ onSearch }) {
  const [query, setQuery] = useState('')
  const [category, setCategory] = useState('')
  const [tag, setTag] = useState('')

  const submit = (e) => {
    e.preventDefault()
    onSearch({ query, category, tag })
  }

  return (
    <form className="search-bar" onSubmit={submit}>
      <input placeholder="Search query" value={query} onChange={(e) => setQuery(e.target.value)} />
      <select value={category} onChange={(e) => setCategory(e.target.value)}>
        <option value="">All Categories</option>
        {['EXCEPTION','SQL','SPRING','DEVOPS','GENERAL'].map(c => <option key={c} value={c}>{c}</option>)}
      </select>
      <input placeholder="Tag" value={tag} onChange={(e) => setTag(e.target.value)} />
      <button type="submit">Search</button>
    </form>
  )
}
