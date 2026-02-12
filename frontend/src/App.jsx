import { Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import HomePage from './pages/HomePage'
import EntryDetailPage from './pages/EntryDetailPage'
import NewEntryPage from './pages/NewEntryPage'
import EditEntryPage from './pages/EditEntryPage'
import SearchPage from './pages/SearchPage'
import AuthGuard from './routes/AuthGuard'

function App() {
  return (
    <div className="app-shell">
      <Navbar />
      <main className="container">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/knowledge/:id" element={<EntryDetailPage />} />
          <Route path="/search" element={<SearchPage />} />
          <Route path="/new" element={<AuthGuard><NewEntryPage /></AuthGuard>} />
          <Route path="/edit/:id" element={<AuthGuard><EditEntryPage /></AuthGuard>} />
        </Routes>
      </main>
    </div>
  )
}

export default App
