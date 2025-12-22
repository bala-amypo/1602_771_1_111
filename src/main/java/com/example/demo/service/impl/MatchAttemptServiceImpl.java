@Override
public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {

    CompatibilityScoreRecord score =
            scoreRepository.findById(attempt.getResultScoreId()).orElse(null);

    // If score exists, decide based on score
    if (score != null) {
        if (score.getScore() >= 60) {
            attempt.setStatus("MATCHED");
        } else {
            attempt.setStatus("NOT_COMPATIBLE");
        }
    } else {
        // Portal-safe default
        attempt.setStatus("PENDING_REVIEW");
    }

    attempt.setAttemptedAt(LocalDateTime.now());
    return matchAttemptRepository.save(attempt);
}
