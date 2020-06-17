import React from 'react';
import '../other/App.css';
import axios from "axios";

const URL = 'http://localhost:8080';

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    //POST запрос на создание игры
    updateUserInfo() {
        axios
            .post(URL + '/login', {
                gameType: this.state.gameType,
                selectedPiece: this.state.selectedPiece,
            }, {withCredentials: true})
            .then(response => {
                console.log(response.data);
                alert(JSON.stringify(response.data));
                window.location.reload(); //перезагрузка страницы при добавлении игры
            })
            .catch(error => {
                console.log(JSON.stringify(error.response.data));
                alert(JSON.stringify(error.response.data));
            });
    }

    handleSubmit(event) {
        this.updateUserInfo();
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit} className="form" id="start-form">
                <h2>Регистрация:</h2>
                <label>Логин:</label>
                <input name="login" type="text" value={this.state.login} onChange={this.handleChange} size="40" required/> <br/>
                <label>Пароль:</label>
                <input name="password" type="text" value={this.state.password} onChange={this.handleChange} size="40" required/> <br/>
                <input type="submit" value="Зарегистрироваться" />
            </form>
        );
    }
}

export default LoginForm;