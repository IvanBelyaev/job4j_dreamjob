package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    private Logger logger = LoggerFactory.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(new FileReader("db.properties"))) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM posts")
        ) {
            ResultSet it = ps.executeQuery();
                while (it.next()) {
                    posts.add(
                            new Post(
                                    it.getInt("id"),
                                    it.getString("name"),
                                    it.getString("description"),
                                    it.getTimestamp("created").toLocalDateTime()
                            ));
                }
        } catch (SQLException e) {
            logger.error("Exception in findAllPosts()", e);
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidates")
        ) {
            ResultSet it = ps.executeQuery();
                while (it.next()) {
                    candidates.add(
                            new Candidate(
                                    it.getInt("id"),
                                    it.getString("name")
                            ));
                }
        } catch (SQLException e) {
            logger.error("Exception in findAllCandidates()", e);
        }
        return candidates;
    }

    /**
     * Deletes candidate.
     * @param id candidate ID.
     */
    @Override
    public void deleteCandidate(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("DELETE FROM candidates WHERE id = ?")
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in deleteCandidate()", e);
        }
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            createPost(post);
        } else {
            updatePost(post);
        }
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            createCandidate(candidate);
        } else {
            updateCandidate(candidate);
        }
    }

    private void updateCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "UPDATE candidates SET name=? where id=?"
             )
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in updateCandidate()", e);
        }
    }

    private Candidate createCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO candidates(name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS
             )
        ) {
            ps.setString(1, candidate.getName());
            ps.executeUpdate();
            ResultSet id = ps.getGeneratedKeys();
            if (id.next()) {
                candidate.setId(id.getInt(1));
            }
        } catch (SQLException e) {
            logger.error("Exception in createCandidate()", e);
        }
        return candidate;
    }

    private Post createPost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO posts(name, description, created) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS
             )
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
            ps.executeUpdate();
            ResultSet id = ps.getGeneratedKeys();
            if (id.next()) {
                post.setId(id.getInt(1));
            }
        } catch (SQLException e) {
            logger.error("Exception in createPost()", e);
        }
        return post;
    }

    private void updatePost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "UPDATE posts SET name=?, description=?, created=? where id=?"
             )
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
            ps.setInt(4, post.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in updatePost()", e);
        }
    }

    @Override
    public Post findPostById(int id) {
        Post post = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM posts WHERE id=?")
        ) {
            ps.setInt(1, id);
            ResultSet it = ps.executeQuery();
            if (it.next()) {
                post = new Post(
                        it.getInt("id"),
                        it.getString("name"),
                        it.getString("description"),
                        it.getTimestamp("created").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            logger.error("Exception in findPostById()", e);
        }
        return post;
    }

    @Override
    public Candidate findCandidateById(int id) {
        Candidate candidate = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidates WHERE id=?")
        ) {
            ps.setInt(1, id);
            ResultSet it = ps.executeQuery();
            if (it.next()) {
                candidate = new Candidate(
                        it.getInt("id"),
                        it.getString("name")
                );
            }
        } catch (SQLException e) {
            logger.error("Exception in findCandidateById()", e);
        }
        return candidate;
    }

    @Override
    public void addUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO users(name, email, pass) VALUES (?, ?, ?)"
             )
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in addUser()", e);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM users WHERE email=?")
        ) {
            ps.setString(1, email);
            try (ResultSet it = ps.executeQuery();) {
                if (it.next()) {
                    user = new User();
                    user.setId(it.getInt("id"));
                    user.setName(it.getString("name"));
                    user.setEmail(it.getString("email"));
                    user.setPassword(it.getString("pass"));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception in findUserByEmail()", e);
        }
        return user;
    }
}
