import KnowledgeCard from './KnowledgeCard'

export default function KnowledgeList({ entries }) {
  if (!entries?.length) return <p>No entries found.</p>
  return <div className="grid">{entries.map((e) => <KnowledgeCard key={e.id} entry={e} />)}</div>
}
