import React,{useState} from "react";
import { Link } from 'react-router-dom';

function SignUp(){
    const [name,setName]=useState("");
    const [city,setCity]=useState("");
    const [street,setStreet]=useState("");
    const [zipcode,setZipcode]=useState("");

    const handleSubmit=(event)=>{

        event.preventDefault();

        const data={
            name:name,
            city:city,
            street:street,
            zipcode:zipcode
        };

        fetch('/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
    };

    const handleChangeName=(event)=>{
        setName(event.target.value);
    };

    const handleChangeCity=(event)=>{
        setCity(event.target.value);
    };

    const handleChangeStreet=(event)=>{
        setStreet(event.target.value);
    };

    const handleChangeZipcode=(event)=>{
        setZipcode(event.target.value);
    };

    return(
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="name">이름:</label>
                <input type="text" id="name" value={name} onChange={handleChangeName} />
            </div>

            <div>
                <label htmlFor="city">도시:</label>
                <input type="text" id="city" value={city} onChange={handleChangeCity} />
            </div>
            <div>
                <label htmlFor="street">거리:</label>
                <input type="text" id="street" value={street} onChange={handleChangeStreet} />
            </div>
            <div>
                <label htmlFor="zipcode">우편번호:</label>
                <input type="text" id="zipcode" value={zipcode} onChange={handleChangeZipcode} />
            </div>

            <button>등록</button>
        </form>
    );
}

export default SignUp;