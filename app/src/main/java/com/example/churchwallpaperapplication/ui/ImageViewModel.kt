package com.example.churchwallpaperapplication.ui

import android.util.JsonReader
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.churchwallpaperapplication.data.ImageUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException
import java.io.InputStream
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ImageViewModel : ViewModel() {
    /**
     * Cupcake state for this order
     */
    private val _uiState = MutableStateFlow(ImageUiState())
    val uiState: StateFlow<ImageUiState> = _uiState.asStateFlow()
    fun parseJSON() {
    public List<Message> readJsonStream(InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            try {
                return readMessagesArray(reader);
            } finally {
                reader.close();
            }
        }

        public List<Message> readMessagesArray(JsonReader reader) throws IOException {
            List<Message> messages = new ArrayList<Message>();

            reader.beginArray();
            while (reader.hasNext()) {
                messages.add(readMessage(reader));
            }
            reader.endArray();
            return messages;
        }

        public Message readMessage(JsonReader reader) throws IOException {
            long id = -1;
            String text = null;
            User user = null;
            List<Double> geo = null;

            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("id")) {
                    id = reader.nextLong();
                } else if (name.equals("text")) {
                    text = reader.nextString();
                } else if (name.equals("geo") &amp;&amp; reader.peek() != JsonToken.NULL) {
                    geo = readDoublesArray(reader);
                } else if (name.equals("user")) {
                    user = readUser(reader);
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return new Message(id, text, user, geo);
        }

        public List<Double> readDoublesArray(JsonReader reader) throws IOException {
            List<Double> doubles = new ArrayList<Double>();

            reader.beginArray();
            while (reader.hasNext()) {
                doubles.add(reader.nextDouble());
            }
            reader.endArray();
            return doubles;
        }

        public User readUser(JsonReader reader) throws IOException {
            String username = null;
            int followersCount = -1;

            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("name")) {
                    username = reader.nextString();
                } else if (name.equals("followers_count")) {
                    followersCount = reader.nextInt();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return new User(username, followersCount);
        }
    }

    fun getScriptureList(): MutableList<String> {
        return mutableListOf("String1", "String2")
    }
}