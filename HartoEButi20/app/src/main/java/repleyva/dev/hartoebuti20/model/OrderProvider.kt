package repleyva.dev.hartoebuti20.model

class OrderProvider {

    // simulamos la data que vendría de una API
    companion object {

        // metodo para retortar todos los combos
        fun getOrder(): List<OrderModel> {
            return order;
        }

        private val order = listOf<OrderModel>(
            OrderModel(
                "Cógela Suave",
                "10 butis + 1 bollo + limón + pimienta + picante",
                6000,
                "https://hartoebuti.herokuapp.com/assets/cogela-suave.jpg"
            ),
            OrderModel(
                "Visajoso",
                "15 butis + 2 bollos + limón + pimienta + picante",
                8000,
                "https://hartoebuti.herokuapp.com/assets/visajoso.jpg"
            ),
            OrderModel(
                "Mi Amor Te Quiero",
                "10 butis + 2 bollos + 5 patacones + 3 chorizos + limón + pimienta + picante",
                10000,
                "https://hartoebuti.herokuapp.com/assets/mi-amor.jpg"
            ),
            OrderModel(
                "Espeluque",
                "20 butis + 2 bollos + 5 patacones + limón + pimienta + picante",
                13000,
                "https://hartoebuti.herokuapp.com/assets/espeluque.jpg"
            ),
            OrderModel(
                "Barrejobo",
                "30 butis + 2 bollos + 10 patacones + 5 chorizos + limón + pimienta + picante",
                22000,
                "https://hartoebuti.herokuapp.com/assets/barrejobo.jpg"
            ),
            OrderModel(
                "Catapila",
                "50 butis + 4 bollos + 20 patacones + 10 chorizos + limón + pimienta + picante",
                30000,
                "https://hartoebuti.herokuapp.com/assets/catapila.jpg"
            ),
        )
    }
}