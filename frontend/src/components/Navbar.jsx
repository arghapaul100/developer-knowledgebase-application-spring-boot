import { Link } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

export default function Navbar() {
  const { user, logout } = useAuth()
  return (
    <nav className="navbar">
      <Link to="/" className="brand">Dev Knowledge Base</Link>
      <div className="nav-links">
        <Link to="/search">Search</Link>
        {user && <Link to="/new">New Entry</Link>}
        {!user ? (
          <>
            <Link to="/login">Login</Link>
            <Link to="/register">Register</Link>
          </>
        ) : (
          <button onClick={logout}>Logout ({user.username})</button>
        )}
      </div>
    </nav>
  )
}
