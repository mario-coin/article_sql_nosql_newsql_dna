CREATE UNIQUE INDEX sequence_hash USING HASH ON sequence (sequenceId, nucleotides(765));