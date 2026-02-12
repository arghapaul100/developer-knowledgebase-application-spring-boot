import { useState } from 'react'
import api from '../api/client'
import SearchBar from '../components/SearchBar'
import KnowledgeList from '../components/KnowledgeList'

export default function SearchPage() {
  const [entries, setEntries] = useState([])

  const onSearch = async (params) => {
    const cleaned = Object.fromEntries(Object.entries(params).filter(([, v]) => v))
    const { data } = await api.get('/knowledge/search', { params: cleaned })
    setEntries(data.content || [])
  }

  return (
    <div className="stack">
      <SearchBar onSearch={onSearch} />
      <KnowledgeList entries={entries} />
    </div>
  )
}
