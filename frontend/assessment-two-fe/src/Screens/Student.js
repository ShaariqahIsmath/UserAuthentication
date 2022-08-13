import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./Styles.css";






function Student(){

    const [noteList, setNoteList] = useState([]);
	const [heading, setHeading] = useState("");
	const [description, setDescription] = useState("");
    const [note, setNote] = useState('');

	const API_URL = "http://localhost:8080/api/notes";

    useEffect(() => {
		const getNotes = () => {
			axios.get(API_URL).then((response) => {
				setNoteList(Array.from(response.data.data));
			});
		};
		getNotes();
	}, []);




    const deleteNote = (id) => {
		axios.delete(`${API_URL}/${id}`, {
			headers: {
				"Access-Control-Allow-Origin": "*",
			},
		});
		setNoteList(
			noteList.filter((note) => {
				return note.id !== id;
			})
		);
	};


    return(
        <>
        <br></br>
        <div className="notes">
                <Link to="/addNote">
                    <button id="addNote"> + Add Note </button>
                </Link>
                <br></br>
                <br></br>
                <br></br>

                <div className="noteList">
                    {noteList.map((note) => {
                        return (
                            <div >
                                <div key={note.id} >

                                    <h4>
                                        {note.heading} 
                                    </h4>
                                    <p id="des">
                                        {note.description}
                                    </p>
                                    <div>
                                    <button id="delete" onClick={() => deleteNote(note.id)}>Delete note</button>
                                    </div>
                                    
                                    
                                        <Link to="">
                                            <button id="edit">Edit Note</button>
                                        </Link>
                                    
                                    <hr></hr>

                                </div>
                            </div>
                        );
                    })}


                </div>

            </div>


        </>
    )
}
    export default Student;


