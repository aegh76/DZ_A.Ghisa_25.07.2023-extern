
import furhatos.app.blankskill1.flow.Parent
import furhatos.flow.kotlin.*

val WennNummerFalschPatient : State = state(Parent) {
    onEntry {
        //Bevor das field Patientennummer neu überschrieben wird, wird es genullt.
        Benutzer!!.put("Patientennummer", null)
        GetDigitsKundenochmal(Benutzer!!, this.furhat)
        goto(ValidierungNummerKunde)
    }
}

