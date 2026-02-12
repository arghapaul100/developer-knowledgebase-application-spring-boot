import { useState } from 'react'
import TagSelector from './TagSelector'

const defaults = {
  title: '', category: 'GENERAL', problemDescription: '', solutionDescription: '', codeSnippet: '', tags: '', exceptionSignature: ''
}

export default function KnowledgeForm({ initial = defaults, onSubmit }) {
  const [form, setForm] = useState(initial)

  const setField = (field, value) => setForm((f) => ({ ...f, [field]: value }))

  const submit = async (e) => {
    e.preventDefault()
    await onSubmit(form)
  }

  return (
    <form className="stack" onSubmit={submit}>
      <input required placeholder="Title" value={form.title} onChange={(e) => setField('title', e.target.value)} />
      <select value={form.category} onChange={(e) => setField('category', e.target.value)}>
        {['EXCEPTION','SQL','SPRING','DEVOPS','GENERAL'].map(c => <option key={c} value={c}>{c}</option>)}
      </select>
      <textarea required placeholder="Problem" value={form.problemDescription} onChange={(e) => setField('problemDescription', e.target.value)} />
      <textarea required placeholder="Solution" value={form.solutionDescription} onChange={(e) => setField('solutionDescription', e.target.value)} />
      <textarea placeholder="Code snippet" value={form.codeSnippet} onChange={(e) => setField('codeSnippet', e.target.value)} />
      <TagSelector value={form.tags} onChange={(v) => setField('tags', v)} />
      <input placeholder="Exception signature" value={form.exceptionSignature} onChange={(e) => setField('exceptionSignature', e.target.value)} />
      <button type="submit">Save</button>
    </form>
  )
}
