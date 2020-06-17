import React from 'react';
import axios from "axios";

const URL = 'http://localhost:8080';

//недоделанный элемент
class AdminPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
        };
    }

    componentDidMount() {
        axios
            .get(URL + '/admin')
            .then(response => {
                this.setState({
                    users: response.data
                });
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            });
    }

    formTable(users) {
        let table = [];
        for (let i = 0; i < users.length; i++) {
            let rows = [];
            rows.push(<th>{users[i].id}</th>);
            rows.push(<th>{users[i].username}</th>);
            rows.push(<th>{users[i].password}</th>);
            rows.push(<th>{users[i].roles}</th>);
            table.push(<tr>{rows}</tr>);
        }
        return table;
    }

    render () {
        return (
            <table id="admin-table">
                <thead>Список пользователей</thead>
                <tr>
                    <th>Id</th>
                    <th>UserName</th>
                    <th>Password</th>
                    <th>Roles</th>
                </tr>
                {this.formTable(this.state.users)}
            </table>
        );
    }
}

export default AdminPage;