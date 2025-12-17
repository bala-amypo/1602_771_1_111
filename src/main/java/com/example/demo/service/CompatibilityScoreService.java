public interface CompatibilityScoreService {

    CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId);

    CompatibilityScoreRecord getScoreById(Long id);

    List<CompatibilityScoreRecord> getScoresForStudent(Long studentId);

    List<CompatibilityScoreRecord> getAllScores();
}
