package com.example.planetory
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DiaryAdapter(options: FirestoreRecyclerOptions<Note>, private val context: Context) :
    FirestoreRecyclerAdapter<Note, DiaryAdapter.DiaryViewHolder>(options) {

    companion object {
        fun timestampToString(timestamp: Timestamp): String {
            return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(timestamp.toDate())
        }
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int, note: Note) {
        holder.txtDTitle.text = note.title
        holder.txtDContent.text = note.content
        holder.txtDTime.text = timestampToString(note.timestamp)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DiaryDetails::class.java)
            intent.putExtra("title", note.title)
            intent.putExtra("content", note.content)
            val docId = this.getSnapshots().getSnapshot(position).id
            intent.putExtra("docId", docId)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_diary_item, parent, false)
        return DiaryViewHolder(view)
    }

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDTitle: TextView = itemView.findViewById(R.id.txtDiaryTitle)
        val txtDContent: TextView = itemView.findViewById(R.id.txtDiaryContent)
        val txtDTime: TextView = itemView.findViewById(R.id.txtDiaryTime)
    }

}