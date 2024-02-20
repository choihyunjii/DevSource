import React, { useState, useEffect } from 'react';

function ProjectApiFetch() {
    const [data, setData] = useState([]);
    const [profile, setProfile] = useState({
        id: 1,
        name: "주동호",
        phoneNumber: "010-7761-8482",
        email: "launcher37@naver.com",
        password: "1234"
    });

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/project/profile/${profile.id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();

            const dataArray = Array.isArray(responseData) ? responseData : [responseData]; // 받은 데이터가 배열이 아니면 배열로 변환
            setData(dataArray);

        } catch (error) {
            console.error('Error fetching data:', error);
            setProfile()
        }
    };


    return (
        <div>
            <h1>데이터:</h1>
            <ul>
                {data.map((item, index) => (
                    <li key={index}>{item.name}</li>
                ))}


            </ul>
        </div>
    );
}

export default ProjectApiFetch;
