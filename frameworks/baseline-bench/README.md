# baseline-bench
Benchmark configuration for the minimal JWT verifier.
  
 * Single JWT issue, 
 * Single JWK
 * Per-key cache
 * Efficient header comparison
   * Check if dot at correct length
   * Compare byte range with the most entropy first
 * Efficient signature check 
   * Precalculated SHA256 header hash 
   * Per-thread initialized digest
 
 Most of the CPU time (> 95%) is spent verifying the signature, so two alternative crypto providers are tested. 