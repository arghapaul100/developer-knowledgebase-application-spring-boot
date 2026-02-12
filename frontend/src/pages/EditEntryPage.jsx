import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api/client'
import KnowledgeForm from '../components/KnowledgeForm'

export default function EditEntryPage() {
  const { id } = useParams()
  const [entry, setEntry] = useState(null)
  const navigate = useNavigate()

  useEffect(() => {
    api.get(`/knowledge/${id}`).then(({ data }) => setEntry(data))
  }, [id])

  const submit = async (payload) => {
    await api.put(`/knowledge/${id}`, payload)
    navigate(`/knowledge/${id}`)
  }

  if (!entry) return <p>Loading...</p>
  return <KnowledgeForm initial={entry} onSubmit={submit} />
}
