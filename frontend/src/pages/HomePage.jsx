import { useEffect, useState } from 'react'
import api from '../api/client'
import KnowledgeList from '../components/KnowledgeList'

export default function HomePage() {
  const [entries, setEntries] = useState([])

  useEffect(() => {
    api.get('/knowledge').then(({ data }) => setEntries(data.content || []))
  }, [])

  return <KnowledgeList entries={entries} />
}
