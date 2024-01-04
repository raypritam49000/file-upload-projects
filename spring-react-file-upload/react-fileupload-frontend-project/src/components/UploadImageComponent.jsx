import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import FileService from '../services/FileService';

const UploadImageComponent = () => {
    const [files, setFiles] = useState(null);
    const [fileUploaded, setFileUploaded] = useState(false);
    const [uploaderName, setUploaderName] = useState('');

    console.log(files);

    const navigate = useNavigate();

    const onFileChange = (event) => {
        setFiles(event.target.files);
    }

    const onUploaderNameChange = (event) => {
        setUploaderName(event.target.value);
    }

    const onUpload = (event) => {
        event.preventDefault();

        const formData = new FormData();

        for (const key of Object.keys(files)) {
            formData.append('files', files[key]);
        }

        formData.append('name', uploaderName);

        FileService.uploadImage(formData)
            .then((response) => {
                console.log(response.data);
                setFileUploaded(true);
            })
            .catch(error => {
                console.log(error);
            });
    }

    if (fileUploaded) {
        navigate('/my-images', { replace: true });
        return null; // or some loading indicator while navigating
    }

    return (
        <div className='row'>
            <div className='card col-md-6 offset-md-3 mt-5'>
                <h3 className='text-center'>Upload Image</h3>
                <div className='card-body'>
                    <form onSubmit={onUpload}>
                        <div>
                            <label>Select a file:</label>
                            <input className='mx-2' type='file' name='file' onChange={onFileChange} multiple></input>
                        </div>

                        <div className="mt-3">
                            <label>Uploader Name:</label>
                            <input className='mx-2' type='text' name='uploaderName' value={uploaderName} onChange={onUploaderNameChange}></input>
                        </div>
                        <button className='btn btn-success btn-sm mt-3' type='submit' disabled={!files || !uploaderName}>Upload</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default UploadImageComponent;
