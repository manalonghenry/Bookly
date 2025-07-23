import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookly.BookDoc
import coil.compose.AsyncImage


@Composable
fun ListBookCard(book: BookDoc, modifier: Modifier = Modifier){
    val authors = book.author_name?.joinToString(", ") ?: "Unknown Author"

    val coverUrl = book.cover_i?.let{
        "https://covers.openlibrary.org/b/id/$it-M.jpg"
    }

    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(modifier = Modifier.padding(16.dp).padding(16.dp)){
            if(coverUrl != null){
                AsyncImage(
                    model = coverUrl,
                    contentDescription = "${book.title} cover",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = book.title ?: "Untitled", style = MaterialTheme.typography.titleMedium, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = authors, style = MaterialTheme.typography.bodySmall, fontSize = 15.sp)
            }
        }
    }
}