import { Message } from 'element-ui';

export default {
    success: (message = '') => {
        Message.success(message);
    },
}