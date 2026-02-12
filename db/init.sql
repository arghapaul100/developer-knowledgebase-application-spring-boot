CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS knowledge_entries (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(40) NOT NULL,
    problem_description TEXT NOT NULL,
    solution_description TEXT NOT NULL,
    code_snippet TEXT,
    tags VARCHAR(600),
    exception_signature VARCHAR(255),
    created_by UUID NOT NULL REFERENCES users(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_knowledge_entries_title ON knowledge_entries(title);
CREATE INDEX IF NOT EXISTS idx_knowledge_entries_category ON knowledge_entries(category);
CREATE INDEX IF NOT EXISTS idx_knowledge_entries_tags ON knowledge_entries(tags);
CREATE INDEX IF NOT EXISTS idx_knowledge_entries_fts ON knowledge_entries
USING GIN (to_tsvector('english', coalesce(title,'') || ' ' || coalesce(problem_description,'') || ' ' || coalesce(solution_description,'') || ' ' || coalesce(tags,'')));
