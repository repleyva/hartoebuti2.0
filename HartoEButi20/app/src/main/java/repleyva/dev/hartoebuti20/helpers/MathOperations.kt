package repleyva.dev.hartoebuti20.helpers

class MathOperations {
    companion object {
        fun add(value: Int): Int {
            return value + 1
        }

        fun less(value: Int): Int {
            if (value < 2) return 1
            return value - 1
        }
    }
}