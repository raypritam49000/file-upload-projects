$(document).ready(function () {
    console.log("Ready...")
    $("#submitBtn").click(function () {
        event.preventDefault();
        const formData = $("#addPersonForm").serialize();
        console.log("formData", formData)

        const formDataArray = $("#addPersonForm").serializeArray();

        const formDataObject = {};

        formDataArray.forEach((field) => {
            formDataObject[field.name] = field.value;
        });

        console.log(formDataObject);

        $.ajax({
            type: "POST",
            url: "/submitPerson",
            data: formData,
            success: function (response) {
                if (response.statusCode === 200) {
                    Toastify({
                        text: response.message,
                        style: {
                            background: "green",
                            position: "center",
                        },
                        className: "toastify-success",
                    }).showToast();

                    document.getElementById("addPersonForm").reset();
                }
            },
            error: function (error) {

                Toastify({
                    text: "Operation failed!",
                    style: {
                        background: "green",
                    },
                    className: "toastify-error",
                }).showToast();

                console.error(error);
            }
        });
    });
});


$(document).ready(function () {
        $("#addPersonForm").validate({
            rules: {
                name: "required",
                email: {
                    required: true,
                    email: true
                },
                city: "required",
                contactNo: {
                    required: true,
                }
            },
            messages: {
                name: "Please enter your name",
                email: {
                    required: "Please enter your email address",
                    email: "Please enter a valid email address"
                },
                city: "Please enter your city",
                contactNo: "Please enter your contact number"
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });