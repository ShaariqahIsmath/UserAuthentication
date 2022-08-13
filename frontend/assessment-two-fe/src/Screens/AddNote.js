import "./Styles.css"
import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";





const AddNote = () => {
    const [note, setNote] = useState('');
    const [heading, setHeading] = useState('');
    const [description, setDescription] = useState('');

    const url = "http://localhost:8080/api/notes"



    const handleSubmit = (e) => {
        e.preventDefault();
        addNotes(heading, description);
    }


    const addNotes = (heading, description) => {
        axios.post(url, {
            heading: heading,
            description: description
        })
            .then((response) => {
                setNote(response.data);
            });
        setHeading("");
        setDescription("");

    }

    return (
        <div>
            <br></br>
            <form>
                <div>
                    <p className="labelN"> Enter Title </p>
                    <input className="form_input"
                        name="title"
                        type="text"
                        placeholder="Enter title"
                        value={heading}
                        onChange={(e) => setHeading(e.target.value)}
                        required

                    /></div><br></br>
                <div>
                    <p className="labelN"> Enter Description </p>
                    <input className="form_input"
                        name="description"
                        type="text"
                        placeholder="Enter description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required

                    />
                </div><br></br>

                <div className="button">
                    <button onClick={handleSubmit} type="submit">Add Note</button>

                </div>

                <Link to="/student">
                    <button id="back">Back</button>
                </Link>
                <br></br>
            </form>
        </div>
    )

}

export default AddNote;