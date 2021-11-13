package repleyva.dev.hartoebuti20.helpers

class MathOperations {
    companion object {
        fun add(value: Int): Int {
            if (value != null) {
                return value + 1
            }
            return value
        }

        fun less(value: Int): Int {
            if (value != null) {
                if (value < 2) {
                    return 1
                } else {
                    return value - 1
                }
            }
            return value
        }
    }
}