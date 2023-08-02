import furhatos.nlu.Intent

//Erklärung: Vergleiche Klasse "nlu.Ja"
class FrageWiederholen : Intent() {

    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Wiederholen", "Kanst du die frage wiederholen", "Nochmal bitte", "Kannst du das nochmal sagen",
            "Das habe ich nicht verstanden", "Was", "Nochmal", "wie bitte", "Was sagt er?",
            "Kannst du das bitte nochmal sagen", "Was sagtest du", "Hä", "Wie bitte", "Was hat er gesagt",
            "Was hast du gesagt", "Können Sie die Information nochmal wiederholen",
            "Kannst du die Information nochmal wiederholen", "Nochmal bitte", "Wie bitte", "Was",
            "Repetați", "Puteți repeta întrebarea", "Din nou, vă rog", "Puteți spune asta din nou",
            "Nu am înțeles asta", "Ce", "Din nou", "Îmi cer scuze", "Ce spune?",
            "Puteți repeta, vă rog", "Ce ați spus", "Huh", "Scuzați-mă", "Ce a spus",
            "Ce ați spus", "Puteți repeta informația",
            "Puteți repeta informația din nou", "Din nou, vă rog", "Cum vă rog", "Ce",
            "Tekrar et", "Soruyu tekrar edebilir misin", "Tekrar lütfen", "Tekrar söyleyebilir misin",
            "Anlamadım", "Ne", "Tekrar", "Özür dilerim", "Ne diyor?",
            "Tekrar söyler misiniz lütfen", "Ne dediniz", "Ha", "Affedersiniz", "Ne dedi",
            "Ne dediniz", "Bilgiyi tekrar edebilir misiniz",
            "Bilgiyi tekrar edebilir misiniz", "Tekrar lütfen", "Nasıl lütfen", "Ne",
            "Repeat", "Can you repeat the question", "Again please", "Can you say that again",
            "I didn't understand that", "What", "Again", "What did you say", "What is he saying?",
            "Can you say that again please", "What did you say", "Huh", "What please", "What did he say",
            "What did you say", "Can you repeat the information again",
            "Can you repeat the information again", "Again please", "How please", "What"
        )
    }
}




