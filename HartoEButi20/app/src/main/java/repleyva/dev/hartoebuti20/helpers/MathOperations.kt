package repleyva.dev.hartoebuti20.helpers

class MathOperations {
    companion object {
        fun add(value: Int): Int {
            var v = value + 1
            return v
        }

        fun less(value: Int): Int {
            var v = value - 1
            if (v < 2) {
                return 1
            } else {
                return v
            }
        }
    }
}