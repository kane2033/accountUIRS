import React from 'react';
import axios from "axios";

const URL = 'http://localhost:8080/api/user-movies/';
//в заголовок каждого запроса добавляется токен
axios.defaults.headers.common['Authorization'] = 'Bearer_' + localStorage.getItem("token");
const MovieStateEnum = Object.freeze({"WATCHED": 1, "PLANNED": 2, "DROPPED": 3})

class MoviesPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userMovies: [],
            statusMsg: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        axios
            .get(URL + 'all', {
                params: {
                    username: localStorage.getItem("username")
                }
            })
            .then(response => {
                this.setState({
                    userMovies: response.data
                });
                console.log(response);
            })
            .catch(error => {
                this.setState({
                    statusMsg: error.response.reason
                })
                console.log(error);
            });
    }

    updateMovies() {
        axios
            .post(URL + 'update', {
                username: localStorage.getItem("username"),
                firstName: this.state.firstName,
                secondName: this.state.secondName,
                lastName: this.state.lastName,
                email: this.state.email,
                birthday: this.state.birthday,
            })
            .then(response => {
                console.log(response.data);
                this.setState({
                    statusMsg: "Изменения успешно применены"
                })
            })
            .catch(error => {
                console.log(error);
                this.setState({
                    statusMsg: error.response.reason
                })
            });
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    //метод обработки кнопки подтверждения формы
    handleSubmit(event) {
        this.updateMovies();
        event.preventDefault();
    }

    formTable(userMovies) {
        let table = [];
        for (let i = 0; i < userMovies.length; i++) {
            let rows = [];
            rows.push(<th>{userMovies[i].movie.name}</th>);
            rows.push(<th><select value={this.state.userMovies[i].movieStatus} onChange={this.handleChange}>
                <option value={MovieStateEnum.WATCHED}>Просмотрено</option>
                <option value={MovieStateEnum.PLANNED}>Запланировано</option>
                <option value={MovieStateEnum.DROPPED}>Прекратил просмотр</option>
            </select></th>)
            rows.push(<th><select value={this.state.userMovies[i].rating} onChange={this.handleChange}>
                {this.formOneToTenSelect()}</select></th>)
            table.push(<tr>{rows}</tr>);
        }
        return table;
    }

    formOneToTenSelect() {
        let rows = [];
        rows.push()
        for (let i = 1; i <= 10; i++) {
            rows.push(<option value={i}>{i}</option>)
        }
        return rows;
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit} className="form" id="user-movies-form">
                {/*<h1>Вход в личный кабинет:</h1>*/}
                <table id="user-movies-table">
                    <thead>Список моих фильмов</thead>
                    <tr>
                        <th>Название фильма</th>
                        <th>Статус</th>
                        <th>Оценка</th>
                    </tr>
                    {this.formTable(this.state.userMovies)}
                </table>
                <div>{this.state.statusMsg}</div>
                <input type="submit" value="Сохранить изменения"/>
            </form>
        );
    }
}

export default MoviesPage;