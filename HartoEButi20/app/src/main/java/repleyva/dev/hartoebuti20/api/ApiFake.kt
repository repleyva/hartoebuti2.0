package repleyva.dev.hartoebuti20.api

import repleyva.dev.hartoebuti20.model.OrderData

class ApiFake {
    companion object {
        fun getData(data: ArrayList<OrderData>) {
            data.add(
                OrderData(
                    "Cógela Suave",
                    "10 butis + 1 bollo + limón + pimienta + picante",
                    "6000",
                    "https://hartoebuti.herokuapp.com/assets/cogela-suave.jpg"
                )
            )
            data.add(
                OrderData(
                    "Visajoso",
                    "15 butis + 2 bollos + limón + pimienta + picante",
                    "8000",
                    "https://hartoebuti.herokuapp.com/assets/visajoso.jpg"
                )
            )
            data.add(
                OrderData(
                    "Mi Amor Te Quiero",
                    "10 butis + 2 bollos + 5 patacones + 3 chorizos + limón + pimienta + picante",
                    "10000",
                    "https://hartoebuti.herokuapp.com/assets/mi-amor.jpg"
                )
            )
            data.add(
                OrderData(
                    "Espeluque",
                    "20 butis + 2 bollos + 5 patacones + limón + pimienta + picante",
                    "13000",
                    "https://hartoebuti.herokuapp.com/assets/espeluque.jpg"
                )
            )
            data.add(
                OrderData(
                    "Barrejobo",
                    "30 butis + 2 bollos + 10 patacones + 5 chorizos + limón + pimienta + picante",
                    "22000",
                    "https://hartoebuti.herokuapp.com/assets/barrejobo.jpg"
                )
            )
            data.add(
                OrderData(
                    "Catapila",
                    "20 butis + 2 bollos + 5 patacones + limón + pimienta + picante",
                    "30000",
                    "https://hartoebuti.herokuapp.com/assets/catapila.jpg"
                )
            )
        }
    }
}