package khanesh.shared.storage

class TransactionRunner(
    private val storage: Storage
) {

    operator fun <T> invoke(block: () -> T): T = storage.transactionWithResult { block() }
}
