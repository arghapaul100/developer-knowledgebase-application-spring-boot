import { useNavigate } from 'react-router-dom'
import api from '../api/client'
import KnowledgeForm from '../components/KnowledgeForm'

export default function NewEntryPage() {
  const navigate = useNavigate()
  const submit = async (payload) => {
    const { data } = await api.post('/knowledge', payload)
    navigate(`/knowledge/${data.id}`)
  }
  return <KnowledgeForm onSubmit={submit} />
}
