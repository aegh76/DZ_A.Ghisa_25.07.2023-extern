
import furhatos.records.User
import java.io.BufferedReader
import java.io.IOException
import furhatos.flow.kotlin.Furhat
import jcifs.CIFSContext
import jcifs.config.PropertyConfiguration
import jcifs.context.BaseContext
import jcifs.smb.NtlmPasswordAuthenticator
import jcifs.smb.SmbFile
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//Die Funktion ReadExcel wurde extra für das Excelformat des Patientenbelegungsplans geschrieben, sodass es beim
//Verstehen des Codes absolut notwendig ist, das Format des Patientenbelegungsplans zu kennen.

fun ReadExcel(benutzer: User,
              furhat: Furhat,
              username: String,
              password: String,
              networkDrivePath: String
)
{



    val config = PropertyConfiguration(System.getProperties())
    val context = BaseContext(config)
    val credentials = NtlmPasswordAuthenticator(username, password)

    val cifsContext: CIFSContext = context.withCredentials(credentials)

    val file = SmbFile(networkDrivePath, cifsContext)
    val inputStream = file.inputStream


    val separator = ";"


    var searchNum: String = benutzer.get("Patientennummer").toString()


    benutzer.put("raum", null)


    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(formatter)




    try {
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            var line: String?
            // Überspringe die erste Zeile (Kopfzeile)
            reader.readLine()



            while (reader.readLine().also { line = it } != null) {

                val data = line!!.split(separator)
                val currentPatNr = data[1].trim()
                val raum = data[3].trim()
                val Beginn =data[5].substring(raum.length - 6)
                val Terminbeginn = data[5].substring(0, 10)



                if (currentPatNr == searchNum && formattedDate == Terminbeginn) {

                        val raum2 = raum.substring(raum.length - 6)
                        val platz = data[4].trim()
                        val Ende = data[6].substring(raum.length - 7)
                        val Vorname = data[7]
                        val Nachname = data[8]
                        val Name = Vorname + " " + Nachname
                        benutzer.put("raum", raum2)
                        benutzer.put("platz", platz)
                        benutzer.put("dialysebeginn", Beginn)
                        benutzer.put("dialyseende", Ende)
                        benutzer.put("name", Name)
                        benutzer.put ("datum", Terminbeginn)
                        println(raum2)
                        println(platz)
                        println(Beginn)
                        print(Ende)

                }
            }
        }
    } catch (e: IOException) {
        furhat.say("Nicht gefunden")
        e.printStackTrace()
    }
}


