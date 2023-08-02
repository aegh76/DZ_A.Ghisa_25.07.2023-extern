import furhatos.nlu.Intent

//Erklärung: Vergleiche Klasse "nlu.Ja"
class Danke : Intent() {

    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Vielen Dank",
            "Danke, gleichfalls",
            "Danke",
            "Gleichfalls",
            "ebenfalls",
            "den wünsche ich auch",
            "Thank you very much.",
            "Thank you, likewise",
            "Thank you.",
            "likewise",
            "likewise",
            "I wish you the same",
            "Çok teşekkür ederim",
            "Teşekkür ederim, aynı şekilde",
            "Teşekkür ederim.",
            "Aynı şekilde",
            "Aynı şekilde",
            "Ben de bunu isterdim",
            "Vă mulțumesc foarte mult",
            "Mulțumesc, la fel",
            "Mulțumesc",
            "la fel",
            "la fel",
            "Și eu aș vrea asta"
        )
    }
}

