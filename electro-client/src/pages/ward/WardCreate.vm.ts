import { useState } from 'react';
import { useForm, zodResolver } from '@mantine/form';
import WardConfigs from 'pages/ward/WardConfigs';
import DistrictConfigs from 'pages/district/DistrictConfigs';
import { WardRequest, WardResponse } from 'models/Ward';
import { DistrictResponse } from 'models/District';
import { SelectOption } from 'types';
import useCreateApi from 'hooks/use-create-api';
import useGetAllApi from 'hooks/use-get-all-api';

function useWardCreateViewModel() {
  const form = useForm({
    initialValues: WardConfigs.initialCreateUpdateFormValues,
    schema: zodResolver(WardConfigs.createUpdateFormSchema),
  });

  const [districtSelectList, setDistrictSelectList] = useState<SelectOption[]>([]);

  const createApi = useCreateApi<WardRequest, WardResponse>(WardConfigs.resourceUrl);
  useGetAllApi<DistrictResponse>(DistrictConfigs.resourceUrl, DistrictConfigs.resourceKey,
    { all: 1 },
    (districtListResponse) => {
      const selectList: SelectOption[] = districtListResponse.content.map((item) => ({
        value: String(item.id),
        label: item.name,
      }));
      setDistrictSelectList(selectList);
    }
  );

  const handleFormSubmit = form.onSubmit((formValues) => {
    const requestBody: WardRequest = {
      name: formValues.name,
      code: formValues.code,
      districtId: Number(formValues.districtId),
    };
    createApi.mutate(requestBody);
  });

  return {
    form,
    handleFormSubmit,
    districtSelectList,
  };
}

export default useWardCreateViewModel;
