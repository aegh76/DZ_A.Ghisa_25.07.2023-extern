package furhatos.app.demo02.nluAlt

import furhatos.nlu.Intent

//Die Klassen des Typs intent definieren einzelne Intensionen und fassen diese zusammen. Sagt ein
//Kunde also "Dialysepatient" oder "Patient" stellen diese beiden Aussagen trotzdem die gleichen Intensionen dar.
//In Intents sammelt man also Ausdrucksweisen, die man unter die gleichen intensionen zusammenfassen kann.
//Das ermöglicht, dass Furhat die intension "ja" verstehen kann, obwohl der Gesprächspartner beispielsweise
//"Das ist richtig" gesagt hat
class Deutsch : Intent() {
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Deutsch",
            "Ich bin deutscher",
            "In Deutsch bitte"
        )
    }
}



