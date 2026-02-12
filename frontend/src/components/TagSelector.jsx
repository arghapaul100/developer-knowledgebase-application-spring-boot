export default function TagSelector({ value, onChange }) {
  return <input placeholder="comma,separated,tags" value={value} onChange={(e) => onChange(e.target.value)} />
}
